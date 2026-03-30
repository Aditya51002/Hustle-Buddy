package com.example.hustlebuddy.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hustlebuddy.model.*

@Composable
fun StudyScoreCard(score: Int, modifier: Modifier = Modifier) {
    // TODO: x1 Calculate score from backend data
    val animatedScore by animateIntAsState(targetValue = score, animationSpec = tween(1000), label = "score")
    
    StudyBuddyCard(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Study Score",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "You're doing great!",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = animatedScore.toString(),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { animatedScore / 100f },
                    modifier = Modifier.size(80.dp),
                    strokeWidth = 8.dp,
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                )
                Icon(
                    Icons.Default.AutoGraph,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun XPProgressBar(xp: Int, level: String, modifier: Modifier = Modifier) {
    // TODO: x3 Store XP and level in backend
    val nextLevelXp = 2000
    val progress by animateFloatAsState(targetValue = xp.toFloat() / nextLevelXp, label = "xp")

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = CircleShape,
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "Lvl",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = level, fontWeight = FontWeight.Bold)
            }
            Text(text = "$xp / $nextLevelXp XP", style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape),
            color = MaterialTheme.colorScheme.tertiary,
            trackColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f)
        )
    }
}

@Composable
fun InsightCard(insight: Insight) {
    // TODO: x2 Generate insights using backend/AI
    val color = when (insight.type) {
        InsightType.POSITIVE -> Color(0xFF4CAF50)
        InsightType.ALERT -> Color(0xFFF44336)
        InsightType.NEUTRAL -> Color(0xFF2196F3)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = color.copy(alpha = 0.2f),
                shape = CircleShape,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = when(insight.icon) {
                            "NightsStay" -> Icons.Default.NightsStay
                            "Warning" -> Icons.Default.Warning
                            else -> Icons.Default.EventBusy
                        },
                        contentDescription = null,
                        tint = color
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = insight.title, fontWeight = FontWeight.Bold, color = color)
                Text(text = insight.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun HeatmapGrid(data: List<Float>) {
    // TODO: x4 Load real study activity data
    Column {
        Text(
            text = "Study Heatmap",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(10),
            modifier = Modifier.height(120.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            userScrollEnabled = false
        ) {
            items(data) { intensity ->
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = intensity.coerceAtSelf(0.1f, 1f)))
                )
            }
        }
    }
}

private fun Float.coerceAtSelf(min: Float, max: Float): Float {
    return if (this < min) min else if (this > max) max else this
}

@Composable
fun ChallengeCard(challenge: Challenge) {
    // TODO: x9 Generate challenges dynamically
    StudyBuddyCard {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = if (challenge.isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                contentDescription = null,
                tint = if (challenge.isCompleted) Color(0xFF4CAF50) else MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = challenge.title,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (challenge.isCompleted) androidx.compose.ui.text.style.TextDecoration.LineThrough else null
                )
                Text(text = challenge.description, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { challenge.progress },
                    modifier = Modifier.fillMaxWidth().height(4.dp).clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "+${challenge.rewardXp}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary)
                Text(text = "XP", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun SubjectAnalyticsCard(subject: Subject) {
    // TODO: x8 Calculate subject analytics
    StudyBuddyCard {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(subject.color).copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Book, contentDescription = null, tint = Color(subject.color))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = subject.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "${subject.tasksCompleted} tasks • ${subject.studyTimeHours}h study", style = MaterialTheme.typography.bodySmall)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${subject.avgQuizScore}%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = if (subject.avgQuizScore > 80) Color(0xFF4CAF50) else if (subject.avgQuizScore > 60) Color(0xFFFFB74D) else Color(0xFFF44336)
                )
                Text(text = "Avg. Quiz", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun DailyGoalItem(goal: DailyGoal) {
    // TODO: x6 Save and track goals
    val progress by animateFloatAsState(targetValue = (goal.currentValue / goal.targetValue).coerceIn(0f, 1f), label = "goal")
    
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = goal.title, fontWeight = FontWeight.Medium)
            Text(text = "${goal.currentValue.toInt()} / ${goal.targetValue.toInt()} ${goal.unit}")
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun ProductivityComparisonCard() {
    // TODO: x12 Compare analytics data
    StudyBuddyCard(containerColor = MaterialTheme.colorScheme.surface) {
        Text(text = "Productivity Comparison", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            ComparisonItem(label = "Today", value = "4.5h", trend = "+15%", isPositive = true)
            VerticalDivider(modifier = Modifier.height(40.dp))
            ComparisonItem(label = "Yesterday", value = "3.8h", trend = "-5%", isPositive = false)
        }
    }
}

@Composable
fun ComparisonItem(label: String, value: String, trend: String, isPositive: Boolean) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = if (isPositive) Icons.Default.TrendingUp else Icons.Default.TrendingDown,
                contentDescription = null,
                tint = if (isPositive) Color(0xFF4CAF50) else Color(0xFFF44336),
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = trend,
                fontSize = 12.sp,
                color = if (isPositive) Color(0xFF4CAF50) else Color(0xFFF44336)
            )
        }
    }
}
